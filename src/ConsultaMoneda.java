import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    private String apiKey = "c6d020d9a31a5adb433a9ad7";

    Moneda convertirMoneda(String MonedaInicial, String MonedaACambiar)  {

//        https://v6.exchangerate-api.com/v6/c6d020d9a31a5adb433a9ad7/latest/USD
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"+ apiKey+"/pair/" + MonedaInicial + "/" + MonedaACambiar);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> respuesta= client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(respuesta.body(), Moneda.class);

        } catch (Exception e) {
            throw new RuntimeException("No se encontro esa Moneda.");
        }


    }
}
