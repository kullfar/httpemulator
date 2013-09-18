package ru.hh.http.emulator.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;
import ru.hh.http.emulator.client.entity.HttpEntry;

public class EmulatorClient {
  private static final String PUT_SIMPLE_PATH = "/criteria/simple";

  private final HttpClient client = new HttpClient();

  private final String url;

  private final ObjectMapper jsonMapper = new ObjectMapper();

  public EmulatorClient(String url) throws Exception {
    this.url = url;
    client.start();
  }

  public SimpleCriteriaBuilder createSimpleRule() {
    return new SimpleCriteriaBuilder(this);
  }

  protected Request newRequest() {
    return client.newRequest(url);
  }

  protected ContentResponse putSimple(final HttpEntry rule, final Collection<HttpEntry> responseEntries) throws JsonProcessingException,
    InterruptedException, TimeoutException, ExecutionException {
    return newRequest().path(PUT_SIMPLE_PATH)
    .method(HttpMethod.PUT)
    .param("rule", jsonMapper.writeValueAsString(rule))
    .param("response", jsonMapper.writeValueAsString(responseEntries))
    .send();
  }
}
