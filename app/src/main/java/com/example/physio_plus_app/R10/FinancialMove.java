package com.example.physio_plus_app.R10;

import android.widget.LinearLayout;

import com.example.physio_plus_app.Pararms.PhysioCenter;
import com.example.physio_plus_app.Pararms.Session;

public class FinancialMove {
    private PhysioCenter physioCenter;
    private Session session;
    public FinancialMove(PhysioCenter physioCenter, Session session) {
        this.physioCenter = physioCenter;
        this.session = session;
    }

    public void show(LinearLayout linearLayout) {
        LinearLayout fm_linearLayout = new LinearLayout(linearLayout.getContext());
        fm_linearLayout.setOrientation(LinearLayout.VERTICAL);
        this.physioCenter.show(fm_linearLayout);
        this.session.show(fm_linearLayout);
        linearLayout.addView(fm_linearLayout);
    }
}
