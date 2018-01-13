package com.example.joans.timetracker;

import java.util.Comparator;

public class DadesActivitatComparator implements Comparator<PackDadesActivitatPosition> {
@Override
public int compare(PackDadesActivitatPosition a, PackDadesActivitatPosition b) {
        DadesActivitat da1 = a.getDades();
        DadesActivitat da2 = b.getDades();
        return da1.getNom().toLowerCase().compareTo(da2.getNom().toLowerCase());
    }
}
