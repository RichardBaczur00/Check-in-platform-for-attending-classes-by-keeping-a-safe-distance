package uvt.projibm.checkin.models;

public class Filter {
    private String field;
    private String data;

    public Filter(String field, String data) {
        this.field = field;
        this.data = data;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
