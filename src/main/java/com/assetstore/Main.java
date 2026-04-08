import com.assetstore.network.Request;
import com.assetstore.network.handler.BodyHandler;
import com.assetstore.network.publisher.BodyPublisher;

public void main() {

    Request request = new Request.Builder()
            .uri("/test")
            .header("Accept", "application/json")
            .POST(BodyPublisher.ofString("{\"name\":\"test\"}"))
            .build();

    BodyHandler<String> bodyHandler = BodyHandler.ofString();

    try{
        bodyHandler.processChunk(request.body().nextChunk());
        String result = bodyHandler.getBody();
        System.out.println(result);
    }
    catch (IOException e){
        e.printStackTrace();
    }

}
