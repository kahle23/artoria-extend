package artoria.action.similarity.support;

import artoria.action.handler.AbstractClassicActionHandler;
import artoria.action.similarity.TextSimilarity;
import artoria.util.CollectionUtils;
import artoria.util.StringUtils;
import org.xm.similarity.text.CosineSimilarity;
import org.xm.tokenizer.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static artoria.util.ObjectUtils.cast;

/**
 * TextSimilarityHandler.
 * @see <a href="https://github.com/shibing624/similarity">Similarity</a>
 */
public class XmTextSimilarityHandler extends AbstractClassicActionHandler {
    private static CosineSimilarity cosineSimilarity = new CosineSimilarity();

    @Override
    public <T> T execute(Object input, Class<T> clazz) {
        isSupport(new Class[]{Double.class}, clazz);
        TextSimilarity textSimilarity = (TextSimilarity) input;
        List<String> words1 = textSimilarity.getWords1();
        List<String> words2 = textSimilarity.getWords2();
        String algorithm = textSimilarity.getAlgorithm();
        String text1 = textSimilarity.getText1();
        String text2 = textSimilarity.getText2();

        if (CollectionUtils.isNotEmpty(words1) &&
                CollectionUtils.isNotEmpty(words2)) {
            return cast(cosineSimilarity.getSimilarity(convert(words1), convert(words2)));
        }
        else if (StringUtils.isNotBlank(text1) &&
                StringUtils.isNotBlank(text2)) {
            return cast(cosineSimilarity.getSimilarity(text1, text2));
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    protected List<Word> convert(List<String> words) {
        if (CollectionUtils.isEmpty(words)) { return Collections.emptyList(); }
        List<Word> list = new ArrayList<Word>();
        for (String word : words) {
            list.add(new Word(word));
        }
        return list;
    }

}
