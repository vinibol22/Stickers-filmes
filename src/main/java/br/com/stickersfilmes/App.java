package br.com.stickersfilmes;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import br.com.stickersfilmes.json.JsonParser;

public class App {

	 public static void main(String[] args) throws Exception {

	        // fazer uma conexão HTTP e buscar os top 250 filmes
	        String url = "https://imdb-api.com/en/API/Top250Movies/k_c90ltqkg";	
	        
          

	        URI endereco = URI.create(url);
	        var client = HttpClient.newHttpClient();
	        var request = HttpRequest.newBuilder(endereco).GET().build();
	        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
	        String body = response.body();

	        // extrair só os dados que interessam (titulo, poster, classificação)
	        var parser = new JsonParser();
	        List<Map<String, String>> listaDeFilmes = parser.parse(body);

           

	        
	        // exibir e manipular os dados 
	        var geradora = new GeradoraDeFigurinhas();

	        String cincoEstrelas = "\u2B50"+"\u2B50"+"\u2B50"+"\u2B50"+"\u2B50";
	        String quatroEstrelas = "\u2B50"+"\u2B50"+"\u2B50"+"\u2B50";
	        String tresEstrelas = "\u2B50"+"\u2B50"+"\u2B50";
	        String duasEstrelas = "\u2B50"+"\u2B50";
	        String umaEstrela = "\u2B50";
	        for (Map<String,String> filme : listaDeFilmes) {
	        	if(listaDeFilmes.size() <= 2) {
	        	String rating = filme.get("imDbRating");
	        	
	        	   String urlImagem = filme.get("image");
	               String titulo = filme.get("title");

	               InputStream inputStream = new URL(urlImagem).openStream();
	               String nomeArquivo = titulo + ".png";

	               geradora.criar(inputStream, nomeArquivo);

	        	
	        	
	        	
	        	
	        	
	        	double ratingDouble = Double.parseDouble(rating);
	        	System.out.println(filme.get("title"));
	            System.out.println(filme.get("image"));
	           
	            
	          //  String titulo = filme.get("title");
	            //String nomeArquivo = titulo + ".png";
	            
	            if(ratingDouble >=8.0) {
	            	rating=cincoEstrelas;
	            	System.out.println(rating);
	            	};
	            if(ratingDouble >= 6.0 && ratingDouble < 8.0) {
	            	rating =quatroEstrelas;
	            	System.out.println(rating);
	            	}
	           if(ratingDouble >= 4.0 && ratingDouble < 6.0) {
	        	   rating = tresEstrelas;
	        	   System.out.println(rating);} 
	           if(ratingDouble >=2.0 && ratingDouble <4.0) {
	        	   rating = duasEstrelas;
	        	   System.out.println(rating);}
	           if(ratingDouble < 2.0 ) {
	        	   rating = umaEstrela;
	        	   System.out.println(rating);}
	           
	            
	        }
	    }}
}
