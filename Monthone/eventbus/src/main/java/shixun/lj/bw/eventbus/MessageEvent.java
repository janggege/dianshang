package shixun.lj.bw.eventbus;

/*
  name:刘江
  data:2019
*/public class MessageEvent {
    private  String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
