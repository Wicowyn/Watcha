package yapiti.watcha.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import yapiti.watcha.entity.Movie;

/**
 * Created by yapiti on 01/02/15.
 */
public class MovieRequestBIs extends SpringAndroidSpiceRequest<MovieRequestBIs.Result> {

    public MovieRequestBIs() {
        super(Result.class);
    }

    @Override
    public Result loadDataFromNetwork() throws Exception {
        MultiValueMap<String, String> map=new LinkedMultiValueMap<String, String>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate template=getRestTemplate();
        template.getMessageConverters().add(new FormHttpMessageConverter());

        return template.postForObject("http://188.166.46.25/api/v1/films", entity, Result.class);
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    @JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.NONE)
    public static class Result {
        private ArrayList<Movie> list=new ArrayList<>();

        public ArrayList<Movie> getList() {
            return list;
        }

        @JsonProperty("result")
        public void setList(ArrayList<Movie> list) {
            this.list = list;
        }
    }

}
