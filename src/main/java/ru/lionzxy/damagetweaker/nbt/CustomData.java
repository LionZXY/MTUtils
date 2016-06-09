package ru.lionzxy.damagetweaker.nbt;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import cpw.mods.fml.common.FMLLog;
import minetweaker.api.data.IData;
import minetweaker.mc1710.data.NBTConverter;
import net.minecraft.nbt.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * Created by lionzxy on 6/8/16.
 */
public abstract class CustomData {

    final static String key = "data";
    private IData data;

    public CustomData() {
        if (!load()) {
            data = NBTConverter.from(getEmptyTag(), false);
            save();
        }
    }

    public IData getData() {
        load();
        return data;
    }

    public void setData(IData data) {
        this.data = data;
        save();
    }


    public IData fromString(String nbt) throws Exception {
        NBTTagCompound nbtTagCompound = (NBTTagCompound) JsonToNBT.func_150315_a(nbt);
        IData tmpData = NBTConverter.from(nbtTagCompound.getTag(key), false);
        return tmpData == null ? NBTConverter.from(getEmptyTag(), false) : tmpData;
    }

    public String toString() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        if (data == null)
            data = NBTConverter.from(getEmptyTag(), false);
        nbtTagCompound.setTag(key, NBTConverter.from(data));

        return getFormatedText(getJson(nbtTagCompound).toString());
    }

    public boolean load() {
        FMLLog.info("Data loading...");
        File jsonFile = getFile();
        if (!jsonFile.exists())
            return false;

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(jsonFile);

            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF-8");
            data = fromString(writer.toString());
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        FMLLog.fine("Data load!");
        return true;
    }

    public void save() {
        FMLLog.info("Data saving...");
        File jsonFile = getFile();
        OutputStream outputStream = null;
        try {
            jsonFile.createNewFile();
            outputStream = new FileOutputStream(jsonFile);

            outputStream.write(toString().getBytes(Charset.forName("UTF-8")));
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        FMLLog.fine("Data saved!");
    }

    public static String getFormatedText(String in) {
        StringBuilder sb = new StringBuilder();
        boolean isIgnore = false;
        int tabCount = 0;
        int b;
        for (int i = 0; i < in.length(); i++) {
            sb.append(in.charAt(i));
            if (in.charAt(i) == '\"')
                isIgnore = !isIgnore;
            if (!isIgnore)
                switch (in.charAt(i)) {
                    case '{':
                    case '[':
                        tabCount++;
                    case ',':
                        sb.append('\n');
                        for (b = 0; b < tabCount; b++)
                            sb.append('\t');
                        break;
                    case '}':
                    case ']':
                        tabCount--;
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append("\n");
                        for (b = 0; b < tabCount; b++)
                            sb.append('\t');
                        sb.append(in.charAt(i));
                }
        }
        return sb.toString();
    }

    public static JsonObject getJson(NBTTagCompound tagCompound) {
        JsonObject jsonObject = new JsonObject();
        for (Iterator iterator = tagCompound.func_150296_c().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            Object object = tagCompound.getTag(key);
            if (object instanceof NBTTagCompound)
                jsonObject.add(key, getJson((NBTTagCompound) object));
            else
                jsonObject.add(key, new Gson().toJsonTree(object));
        }
        return jsonObject;
    }

    public NBTTagCompound getEmptyTag() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString("CustomDataType", getName());
        return nbtTagCompound;
    }

    public String getName() {
        return "Unknown";
    }

    abstract File getFile();
}
