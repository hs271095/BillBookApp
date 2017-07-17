package bangalore.bms.billbookonline;

/**
 * Created by Harshit on 16-07-2017.
 */

public class Bill_Gen {
    private int _id;
    private String _name;
    private String _Add;
    private String _mob;
    private String _email;

    public Bill_Gen() {
    }

    public Bill_Gen(String _name, String _Add, String _mob, String _email) {
        this._name = _name;
        this._Add = _Add;
        this._mob = _mob;
        this._email = _email;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_Add() {
        return _Add;
    }

    public void set_Add(String _Add) {
        this._Add = _Add;
    }

    public String get_mob() {
        return _mob;
    }

    public void set_mob(String _mob) {
        this._mob = _mob;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_name() {

        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
