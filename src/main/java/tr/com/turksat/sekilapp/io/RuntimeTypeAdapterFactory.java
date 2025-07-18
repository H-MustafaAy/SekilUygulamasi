package tr.com.turksat.sekilapp.io;
import com.google.gson.internal.Streams;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


public final class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<?> baseType;
    private final String typeFieldName;
    private final Map<String, Class<?>> labelToSubtype = new LinkedHashMap<>();
    private final Map<Class<?>, String> subtypeToLabel = new LinkedHashMap<>();
    private final boolean maintainType;

    private RuntimeTypeAdapterFactory(Class<?> baseType, String typeFieldName, boolean maintainType) {
        if (typeFieldName == null || baseType == null) {
            throw new NullPointerException();
        }
        this.baseType = baseType;
        this.typeFieldName = typeFieldName;
        this.maintainType = maintainType;
    }


    /**
     * Fabrika oluşturur.
     *
     * @param baseType Temel sınıf
     * @param typeFieldName JSON'da tip bilgisini tutacak alan adı
     * @param <T> Tip parametresi
     * @return RuntimeTypeAdapterFactory örneği
     */
    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName) {
        return new RuntimeTypeAdapterFactory<>(baseType, typeFieldName, false);
    }



    /**
     * Alt türü belirtilen etiketle kaydeder.
     *
     * @param type Alt tür sınıfı
     * @param label JSON'da görünecek tip etiketi
     * @return Bu RuntimeTypeAdapterFactory örneği (method chaining için)
     * @throws NullPointerException type veya label null ise
     * @throws IllegalArgumentException Aynı tip veya etiket daha önce kayıtlıysa
     */

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> type, String label) {
        if (type == null || label == null) {
            throw new NullPointerException();
        }
        if (subtypeToLabel.containsKey(type) || labelToSubtype.containsKey(label)) {
            throw new IllegalArgumentException("Type or label already registered.");
        }
        labelToSubtype.put(label, type);
        subtypeToLabel.put(type, label);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        if (!baseType.isAssignableFrom(type.getRawType())) {
            return null;
        }

        final Map<String, TypeAdapter<?>> labelToDelegate = new LinkedHashMap<>();
        final Map<Class<?>, TypeAdapter<?>> subtypeToDelegate = new LinkedHashMap<>();
        for (Map.Entry<String, Class<?>> entry : labelToSubtype.entrySet()) {
            TypeAdapter<?> delegate = gson.getDelegateAdapter(this, TypeToken.get(entry.getValue()));
            labelToDelegate.put(entry.getKey(), delegate);
            subtypeToDelegate.put(entry.getValue(), delegate);
        }

        return new TypeAdapter<R>() {
            @Override
            public void write(JsonWriter out, R value) throws IOException {
                Class<?> srcType = value.getClass();
                String label = subtypeToLabel.get(srcType);
                TypeAdapter<R> delegate = (TypeAdapter<R>) subtypeToDelegate.get(srcType);
                if (delegate == null) {
                    throw new JsonParseException("Cannot serialize " + srcType.getName() + "; did you forget to register a subtype?");
                }

                JsonObject jsonObject = delegate.toJsonTree(value).getAsJsonObject();

                if (maintainType) {
                    Streams.write(jsonObject, out);
                    return;
                }

                JsonObject clone = new JsonObject();

                clone.add(typeFieldName, new JsonPrimitive(label));

                for (Map.Entry<String, JsonElement> e : jsonObject.entrySet()) {
                    if (!e.getKey().equals(typeFieldName)) {
                        clone.add(e.getKey(), e.getValue());
                    }
                }

                Streams.write(clone, out);
            }

            @Override
            public R read(JsonReader in)  {
                JsonElement jsonElement = Streams.parse(in);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonElement labelJsonElement = jsonObject.remove(typeFieldName);
                if (labelJsonElement == null) {
                    throw new JsonParseException("Cannot deserialize " + baseType.getName() + " because it does not contain field named " + typeFieldName);
                }
                String label = labelJsonElement.getAsString();
                @SuppressWarnings("unchecked")
                TypeAdapter<R> delegate = (TypeAdapter<R>) labelToDelegate.get(label);
                if (delegate == null) {
                    throw new JsonParseException("Cannot deserialize " + baseType.getName() + " subtype named " + label + "; did you forget to register a subtype?");
                }
                return delegate.fromJsonTree(jsonObject);
            }
        }.nullSafe();
    }
}
