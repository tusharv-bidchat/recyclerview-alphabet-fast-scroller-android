package com.viethoa.models;

/**
 * Created by VietHoa on 02/10/15.
 */
public class AlphabetItem {

    public int position;
    public String key;
    public String value;
    public boolean isActive;

    public AlphabetItem(int pos, String key, String value, boolean isActive) {
        this.position = pos;
        this.key = key;
        this.value = value;
        this.isActive = isActive;
    }
}
