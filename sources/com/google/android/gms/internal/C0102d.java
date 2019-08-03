package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;

/* renamed from: com.google.android.gms.internal.d */
public abstract class C0102d<T extends LifecycleDelegate> {
    private static final String TAG = C0102d.class.getSimpleName();
    /* access modifiers changed from: private */

    /* renamed from: cp */
    public T f53cp;
    /* access modifiers changed from: private */

    /* renamed from: cq */
    public Bundle f54cq;
    /* access modifiers changed from: private */

    /* renamed from: cr */
    public LinkedList<C0108a> f55cr;

    /* renamed from: cs */
    private final C0059ag<T> f56cs = new C0117i(this);

    /* renamed from: com.google.android.gms.internal.d$a */
    private interface C0108a {
        /* renamed from: a */
        void mo1320a(LifecycleDelegate lifecycleDelegate);

        int getState();
    }

    /* renamed from: a */
    private void m140a(Bundle bundle, C0108a aVar) {
        if (this.f53cp != null) {
            aVar.mo1320a(this.f53cp);
            return;
        }
        if (this.f55cr == null) {
            this.f55cr = new LinkedList<>();
        }
        this.f55cr.add(aVar);
        if (bundle != null) {
            if (this.f54cq == null) {
                this.f54cq = (Bundle) bundle.clone();
            } else {
                this.f54cq.putAll(bundle);
            }
        }
        mo1309a(this.f56cs);
    }

    /* renamed from: d */
    private void m142d(int i) {
        while (!this.f55cr.isEmpty() && ((C0108a) this.f55cr.getLast()).getState() >= i) {
            this.f55cr.removeLast();
        }
    }

    /* renamed from: a */
    public void mo1308a(FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        String b = GooglePlayServicesUtil.m14b(context, isGooglePlayServicesAvailable, -1);
        String a = GooglePlayServicesUtil.m8a(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(b);
        linearLayout.addView(textView);
        if (a != null) {
            Button button = new Button(context);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(a);
            linearLayout.addView(button);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    context.startActivity(GooglePlayServicesUtil.m7a(context, isGooglePlayServicesAvailable, -1));
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo1309a(C0059ag<T> agVar);

    /* renamed from: aa */
    public T mo1310aa() {
        return this.f53cp;
    }

    public void onCreate(final Bundle bundle) {
        m140a(bundle, (C0108a) new C0108a() {
            /* renamed from: a */
            public void mo1320a(LifecycleDelegate lifecycleDelegate) {
                C0102d.this.f53cp.onCreate(bundle);
            }

            public int getState() {
                return 1;
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        final LayoutInflater layoutInflater2 = layoutInflater;
        final ViewGroup viewGroup2 = viewGroup;
        final Bundle bundle2 = bundle;
        m140a(bundle, (C0108a) new C0108a() {
            /* renamed from: a */
            public void mo1320a(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(C0102d.this.f53cp.onCreateView(layoutInflater2, viewGroup2, bundle2));
            }

            public int getState() {
                return 2;
            }
        });
        if (this.f53cp == null) {
            mo1308a(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.f53cp != null) {
            this.f53cp.onDestroy();
        } else {
            m142d(1);
        }
    }

    public void onDestroyView() {
        if (this.f53cp != null) {
            this.f53cp.onDestroyView();
        } else {
            m142d(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        m140a(bundle2, (C0108a) new C0108a() {
            /* renamed from: a */
            public void mo1320a(LifecycleDelegate lifecycleDelegate) {
                C0102d.this.f53cp.onInflate(activity, bundle, bundle2);
            }

            public int getState() {
                return 0;
            }
        });
    }

    public void onLowMemory() {
        if (this.f53cp != null) {
            this.f53cp.onLowMemory();
        }
    }

    public void onPause() {
        if (this.f53cp != null) {
            this.f53cp.onPause();
        } else {
            m142d(3);
        }
    }

    public void onResume() {
        m140a((Bundle) null, (C0108a) new C0108a() {
            /* renamed from: a */
            public void mo1320a(LifecycleDelegate lifecycleDelegate) {
                C0102d.this.f53cp.onResume();
            }

            public int getState() {
                return 3;
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.f53cp != null) {
            this.f53cp.onSaveInstanceState(bundle);
        } else if (this.f54cq != null) {
            bundle.putAll(this.f54cq);
        }
    }
}
