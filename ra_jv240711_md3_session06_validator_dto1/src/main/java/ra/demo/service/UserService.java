package ra.demo.service;

import ra.demo.dto.request.LoginDtoRequest;
import ra.demo.dto.request.RegisterDtoRequest;
import ra.demo.dto.response.LoginDtoResponse;

public interface UserService {
    LoginDtoResponse findUser(LoginDtoRequest loginDtoRequest);

    boolean create(RegisterDtoRequest registerDtoRequest);
}
