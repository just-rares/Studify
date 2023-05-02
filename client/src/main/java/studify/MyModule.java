package studify;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import studify.controller.AdminCtrl;
import studify.controller.MainCtrl;
import studify.controller.RegisterCtrl;
import studify.utils.ServerUtils;

public class MyModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(MainCtrl.class).in(Scopes.SINGLETON);
        binder.bind(AdminCtrl.class).in(Scopes.SINGLETON);
        binder.bind(RegisterCtrl.class).in(Scopes.SINGLETON);
        binder.bind(ServerUtils.class).in(Scopes.SINGLETON);
    }
}