package com.example.physio_plus_app;

import android.widget.LinearLayout;

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
