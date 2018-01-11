package com.example.joans.timetracker;

import java.util.Comparator;

/**
 * Created by Jiacheny on 2018/1/11.
 */

public class DadesActivitatComparator implements Comparator<PackDadesActivitatPosition> {
@Override
public int compare(PackDadesActivitatPosition a, PackDadesActivitatPosition b) {
        DadesActivitat da1 = a.getDades();
        DadesActivitat da2 = b.getDades();
        return da1.getNom().compareTo(da2.getNom());
    }
}
