package ra.demo.mapper;

/*
 *   R: DTORequest
 *   E: Entity
 *   P: DTOResponse
 * */
public interface GenericMapper<R, E, P> {
    E mapperRequestToEntity(R request);

    P mapperEntityToResponse(E entity);

}
