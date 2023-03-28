import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        
        //Fazer uma conexão HTTP e buscar os dados (TOP10 filmes);
        String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request,BodyHandlers.ofString());

        String body = response.body();

        //Pegar apenas os dados que são de interesse - Título, poster, classificação;
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir e manipular os dados de acordo com o objetivo;
        int contador = 1;
        for (Map<String,String> filme : listaDeFilmes) {
            String estrelas = "";
            String corFonteFundo = "\u001b[30m\u001b[43m";
            String sublinhar = "\u001b[4m";
            String negrito = "\u001b[1m";
            String fechaComando = "\u001b[m";

            System.out.println();
            System.out.println(negrito + sublinhar + corFonteFundo + contador + "º-Título: " + filme.get("title") + fechaComando);
            System.out.println("Poster: " + filme.get("image"));
            for (double i=0.0;i<=Double.parseDouble(filme.get("imDbRating"));i++){
                estrelas = estrelas + "\u2B50";
            }
            System.out.println("Classificação: " + estrelas);
            System.out.println();
            System.out.println("-----------------------------------------------------");
            contador++;
        }

    }  
}