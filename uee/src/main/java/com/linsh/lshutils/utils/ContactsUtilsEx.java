package com.linsh.lshutils.utils;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.linsh.lshutils.entity.ContactsRowInfo;
import com.linsh.utilseverywhere.ContextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2023/04/18
 *    desc   : 通讯录工具
 * </pre>
 */
public class ContactsUtilsEx {

    public static int findContactId(String contactName) {
        //查询条件
        String selection = ContactsContract.Data.DISPLAY_NAME_PRIMARY + " = ?";
        String[] selectionArgs = new String[]{contactName};
        //查询联系人数据
        try (Cursor cursor = ContextUtils.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                null,
                selection,
                selectionArgs,
                null)) {
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getInt(cursor.getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));
            }
            return -1;
        }
    }

    /**
     * 获取联系人信息
     *
     * @param contactName 联系人名字
     */
    public static List<ContactsRowInfo> findContactInfo(String contactName) {
        //查询条件
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = ?";
        String[] selectionArgs = new String[]{contactName};
        //查询联系人数据
        try (Cursor cursor = ContextUtils.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                null,
                selection,
                selectionArgs,
                null)) {
            if (cursor != null && cursor.moveToFirst()) {
                ArrayList<ContactsRowInfo> infos = new ArrayList<>();
                do {
                    int contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));
                    String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
                    String data1 = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DATA1));
                    String data2 = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DATA2));
                    String data3 = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DATA3));
                    infos.add(new ContactsRowInfo(contactId, mimeType, data1, data2, data3));
                } while (cursor.moveToNext());
                return infos;
            }
            return null;
        }
    }
}
