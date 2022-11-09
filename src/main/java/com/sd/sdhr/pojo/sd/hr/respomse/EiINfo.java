package com.sd.sdhr.pojo.sd.hr.respomse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
@Slf4j
public class EiINfo implements Serializable, Cloneable {

    private static final long serialVersionUID = 4406463535024651558L;

    private String success = "";

    private String message = "";

    private long totalNum = 0;

    private int pageNum = 0;

    private int pageSize = 10;

    private List<?> data;

    private Map attr;

    public void set(String key, Object value) {
        if (this.attr == null)
            this.attr = new HashMap<>();
        this.attr.put(key, value);
    }

    public Object get(String key) {
        return (this.attr == null) ? null : this.attr.get(key);
    }
}
