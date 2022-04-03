import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in);
		String code = ler.nextLine();
		GetApiCode apiCode = new GetApiCode(code);
		
		String url = "https://imdb-api.com/en/API/Top250Movies/" + apiCode.getApiCode();
		
		try {
			HttpClient client = HttpClient.newHttpClient();   
			HttpRequest	request = HttpRequest.newBuilder()
			         .uri(new URI(url))
			         .GET()
			         .build();
			 client.sendAsync(request, BodyHandlers.ofString())
	         .thenApply(HttpResponse::body)
	         .thenAccept(System.out::println)
	         .join(); 
		   
			 HttpResponse<String> resp = client.send(request, BodyHandlers.ofString());
			 System.out.println(resp.statusCode());
			 System.out.println(resp.body());
		} catch (Exception e) {
			System.out.println(e);
		} 
			
		ler.close();
	} 
	
}
