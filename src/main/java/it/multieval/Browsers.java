package it.multieval;

import it.multieval.beans.Browser;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@Component
public class Browsers {

    @Value("#{systemProperties['browsers.file'] != null ? 'file:'+systemProperties['browsers.file'] : 'classpath:/browsers.json'}")
    Resource browsers;

    public List<Browser> getAll() throws IOException {
        String text = Resources.toString(browsers.getURL(), Charsets.UTF_8);
        return asList(new Gson().fromJson(text, Browser[].class));
    }
}
