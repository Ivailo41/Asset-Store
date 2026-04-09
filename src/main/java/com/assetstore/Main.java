import com.assetstore.network.SitpRequest;
import com.assetstore.network.handler.BodyHandler;
import com.assetstore.network.publisher.BodyPublisher;

public void main() {

    SitpRequest sitpRequest = new SitpRequest.Builder()
            .url("localhost:8080/test")
            .header("Accept", "application/json")
            .POST(BodyPublisher.ofString("{\"name\":\"test\"}"))
            .build();

    BodyHandler<String> bodyHandler = BodyHandler.ofString();

    try{
        bodyHandler.processChunk(sitpRequest.body().nextChunk());
        String result = bodyHandler.getBody();
        System.out.println(result);
    }
    catch (IOException e){
        e.printStackTrace();
    }

}
