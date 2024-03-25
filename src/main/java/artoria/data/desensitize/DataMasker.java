package artoria.data.desensitize;

/**
 * Provide a high-level abstract of the data masker tools.
 * @author Kahle
 * TODO: 2023/6/2 Deletable
 */
@Deprecated
public interface DataMasker {

    /**
     * Data masking.
     * @param data Data to be masked
     * @return Masked results
     */
    String mask(String data);

}
