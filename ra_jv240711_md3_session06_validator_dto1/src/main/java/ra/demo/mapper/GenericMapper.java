package ra.demo.mapper;

/*
 *   R: DTORequest
 *   E: Entity
 *   P: DTOResponse
 * */
public interface GenericMapper<R, E, P> { // req, Entity , P- response
    E mapperRequestToEntity(R request);// tra về kiểu E nên E là kiểu dữ liệu

    P mapperEntityToResponse(E entity);

}
