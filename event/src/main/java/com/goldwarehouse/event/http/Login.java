package com.goldwarehouse.event.http;

import com.goldwarehouse.common.event.AsyncEvent;
import com.goldwarehouse.common.event.IRemoteEventManager;
import com.goldwarehouse.transport.http.HttpEvent;
import com.goldwarehouse.transport.tools.HttpEventUtil;

import java.util.List;

/**
 * Created by guo_d on 2016/12/26.
 */
public class Login extends HttpEvent {
    private static final long tokenValid = 24 * 60 * 60 * 1000;
    @Override
    public boolean request(IRemoteEventManager eventManager) throws Exception {
        if (!isPost()) {
            HttpEventUtil.returnFail(ctx, ReplyMessage.WRONG_METHOD.getMsg());
            return false;
        }
        Request json = HttpEventUtil.stringToClass(Request.class, content);
//        UserLoginEvent in = new UserLoginEvent(id, null, json.userId, json.pwd, id, UserLoginType.fromInt(json.type),
//                null, true);
//        in.setSender(sender);
//        eventManager.sendEvent(in);
        return true;
    }

    @Override
    public boolean response(AsyncEvent event) throws Exception {
//        if (event instanceof UserLoginReplyEvent) {
//            if (((UserLoginReplyEvent) event).getTxId().equals(id)) {
//                Response res = new Response();
//                res.ok = ((UserLoginReplyEvent) event).isOk();
//                res.message = ((UserLoginReplyEvent) event).getMessage();
//                if (!res.ok) {
//                    String data = HttpEventUtil.classToString(res) + "\r\n";
//                    HttpEventUtil.sendResponse(ctx, data);
//                    return true;
//                }
//                res.user = ((UserLoginReplyEvent) event).getUser();
//                res.defaultAccount = ((UserLoginReplyEvent) event).getDefaultAccount();
//                res.accounts = ((UserLoginReplyEvent) event).getAccounts();
//                String accountId = null;
//                if (res.defaultAccount != null) {
//                    accountId = res.defaultAccount.getId();
//                } else {
//                    accountId = res.accounts.get(0).getId();
//                }
//                res.token = CipherUtil.getInstance().encrypt(res.user.getId(), accountId,
//                        String.valueOf(this.created + tokenValid));
//                String data = HttpEventUtil.classToString(res) + "\r\n";
//                HttpEventUtil.sendResponse(ctx, data);
//            }
//            return true;
//        } else {
            return false;
//        }
    }

    private class Request {
        private String userId;
        private String pwd;
        private int type;
    }

    private class Response {
        private boolean ok;
        private String message;
        private byte[] token;
    }
}
