package RestMock;

public class Response1 extends SomeResponse {

    @Override
    protected String getRespBody(String request) {
        return "<?xml version=\" 1.0 \" encoding=\"utf-8\"?>" +
                "<Service>" +
                "<success>true</success>" +
                "<name>" + request + "</name>" +
                "</Service>";
    }
}
