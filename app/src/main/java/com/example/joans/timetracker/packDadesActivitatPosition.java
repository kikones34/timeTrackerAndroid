package com.example.joans.timetracker;

/**
 * Created by Jiacheny on 2018/1/9.
 */

public class packDadesActivitatPosition {
    private DadesActivitat dades;
    private int pos;

    public packDadesActivitatPosition(DadesActivitat d, int p) {
        dades = d;
        pos = p;
    }

    public DadesActivitat getDades() {
        return dades;
    }

    public int getPos() {
        return pos;
    }

    @Override
    public final String toString() {
        return dades.toString();
    }
}
