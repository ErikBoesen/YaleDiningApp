package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.internal.C0048ab;
import com.google.android.gms.internal.C0059ag;
import com.google.android.gms.internal.C0091at;
import com.google.android.gms.internal.C0102d;
import com.google.android.gms.internal.C0113g;
import com.google.android.gms.internal.C0128n;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class MapFragment extends Fragment {

    /* renamed from: aK */
    private GoogleMap f191aK;

    /* renamed from: cO */
    private final C0178a f192cO = new C0178a(this);

    /* renamed from: com.google.android.gms.maps.MapFragment$a */
    static class C0178a extends C0102d<C0179b> {

        /* renamed from: e */
        private final Fragment f193e;

        /* renamed from: f */
        protected C0059ag<C0179b> f194f;

        /* renamed from: g */
        private Activity f195g;

        C0178a(Fragment fragment) {
            this.f193e = fragment;
        }

        /* access modifiers changed from: private */
        public void setActivity(Activity activity) {
            this.f195g = activity;
            mo1546a();
        }

        /* renamed from: a */
        public void mo1546a() {
            if (this.f195g != null && this.f194f != null && mo1310aa() == null) {
                try {
                    MapsInitializer.initialize(this.f195g);
                    this.f194f.mo1209b(new C0179b(this.f193e, C0128n.m185a((Context) this.f195g).mo1178c(C0048ab.m22a(this.f195g))));
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo1309a(C0059ag<C0179b> agVar) {
            this.f194f = agVar;
            mo1546a();
        }
    }

    /* renamed from: com.google.android.gms.maps.MapFragment$b */
    static class C0179b implements LifecycleDelegate {

        /* renamed from: ah */
        private final IMapFragmentDelegate f196ah;

        /* renamed from: e */
        private final Fragment f197e;

        public C0179b(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate) {
            this.f196ah = (IMapFragmentDelegate) C0091at.m107c(iMapFragmentDelegate);
            this.f197e = (Fragment) C0091at.m107c(fragment);
        }

        /* renamed from: g */
        public IMapFragmentDelegate mo1547g() {
            return this.f196ah;
        }

        public void onCreate(Bundle bundle) {
            if (bundle == null) {
                try {
                    bundle = new Bundle();
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            }
            Bundle arguments = this.f197e.getArguments();
            if (arguments != null && arguments.containsKey("MapOptions")) {
                C0113g.m153a(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
            }
            this.f196ah.onCreate(bundle);
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) C0048ab.m23a(this.f196ah.onCreateView(C0048ab.m22a(layoutInflater), C0048ab.m22a(viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroy() {
            try {
                this.f196ah.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            try {
                this.f196ah.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.f196ah.onInflate(C0048ab.m22a(activity), (GoogleMapOptions) bundle.getParcelable("MapOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onLowMemory() {
            try {
                this.f196ah.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.f196ah.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.f196ah.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.f196ah.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static MapFragment newInstance(GoogleMapOptions googleMapOptions) {
        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googleMapOptions);
        mapFragment.setArguments(bundle);
        return mapFragment;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public IMapFragmentDelegate mo1533g() {
        this.f192cO.mo1546a();
        if (this.f192cO.mo1310aa() == null) {
            return null;
        }
        return ((C0179b) this.f192cO.mo1310aa()).mo1547g();
    }

    public GoogleMap getMap() {
        IMapFragmentDelegate g = mo1533g();
        if (g == null) {
            return null;
        }
        try {
            IGoogleMapDelegate map = g.getMap();
            if (map == null) {
                return null;
            }
            if (this.f191aK == null || this.f191aK.mo1449ab().asBinder() != map.asBinder()) {
                this.f191aK = new GoogleMap(map);
            }
            return this.f191aK;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f192cO.setActivity(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f192cO.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.f192cO.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        this.f192cO.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.f192cO.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        this.f192cO.setActivity(activity);
        GoogleMapOptions createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", createFromAttributes);
        this.f192cO.onInflate(activity, bundle2, bundle);
    }

    public void onLowMemory() {
        this.f192cO.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.f192cO.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.f192cO.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.f192cO.onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
