package com.linsh.lshutils.entity;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2023/04/18
 *    desc   : 通讯录表中的主要行数据信息
 * </pre>
 */
public class ContactsRowInfo {

    // 联系人id
    private final int contactId;
    // 类型
    private final String mimetype;
    private final String data1;
    private final String data2;
    private final String data3;

    public ContactsRowInfo(int contactId, String mimetype, String data1, String data2, String data3) {
        this.contactId = contactId;
        this.mimetype = mimetype;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    public int getContactId() {
        return contactId;
    }

    public String getMimetype() {
        return mimetype;
    }

    public String getData1() {
        return data1;
    }

    public String getData2() {
        return data2;
    }

    public String getData3() {
        return data3;
    }

    @Override
    public String toString() {
        return "ContactsRowInfo{" +
                "contactId='" + contactId + '\'' +
                ", mimetype='" + mimetype + '\'' +
                ", data1='" + data1 + '\'' +
                ", data2='" + data2 + '\'' +
                ", data3='" + data3 + '\'' +
                '}';
    }
}
