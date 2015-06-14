package com.catatron.probe;

import com.catatron.probe.beans.Browser;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@Component
public class Browsers {

    public List<Browser> getAll() throws IOException {
        String text = Resources.toString(Resources.getResource("browsers.json"), Charsets.UTF_8);
        return asList(new Gson().fromJson(text, Browser[].class));
    }
}
