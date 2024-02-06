package com.nataliasep.apifrases.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Frase implements Parcelable {
    private int id;
    private String texto;

    public Frase(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    protected Frase(Parcel in) {
        id = in.readInt();
        texto = in.readString();
    }

    public static final Creator<Frase> CREATOR = new Creator<Frase>() {
        @Override
        public Frase createFromParcel(Parcel in) {
            return new Frase(in);
        }

        @Override
        public Frase[] newArray(int size) {
            return new Frase[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return "Frase{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.texto);

    }
}
