package net.rudahee.archetype.services.crud.entity;

import net.rudahee.archetype.services.crud.dto.AbstractDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCRUDService<E, DTO, C extends AbstractDTOConverter<E, DTO>, R extends JpaRepository<E, ID>, ID> {

    @Autowired
    protected R repository;

    @Autowired
    protected C dtoConverter;

    /* This method save or update an entity from dto, and returns dto.
     *
     * @param d DTO
     *
     * @return DTO
     */
    public DTO save(DTO d) {

        //Convert to Entity to save in BBDD
        E entity = dtoConverter.convertFromDTO(d);

        // Return response in DTO Format.
        return dtoConverter.convertFromEntity(repository.save(entity));
    }

    /* This method save or update an entity, and returns dto.
     *
     * @param entity E
     *
     * @return DTO
     */
    public DTO saveEntity(E entity) {
        // Return response in DTO Format.
        return dtoConverter.convertFromEntity(repository.save(entity));
    }

    /* This method save or update a List<DTO>, and returns List<DTO>.
     *
     * @param d List<DTO>
     *
     * @return List<DTO>
     */
    public List<DTO> saveAll(List<DTO> d) {

        //Convert to Entity to save in BBDD
        List<E> entity = dtoConverter.convertFromDTOs(d);

        // Return response in DTO Format.
        return dtoConverter.convertFromEntities(repository.saveAll(entity));
    }

    /* This method save or update a List<E>, and returns List<DTO>.
     *
     * @param entities List<E>
     *
     * @return List<DTO>
     */
    public List<DTO> saveAllEntities(List<E> entities) {

        // Return response in DTO Format.
        return dtoConverter.convertFromEntities(repository.saveAll(entities));
    }

    /* This method return an Optional<DTO> by id.
     *
     * @param id
     *
     * @return Optional<DTO>
     */
    public Optional<DTO> findById(ID id) {
        Optional<E> entity = repository.findById(id);

        return entity.map(e -> dtoConverter.convertFromEntity(e));
    }

    /* This method return all DTOs.
     *
     * @return List<DTO>
     */
    public List<DTO> findAll() {

        return dtoConverter.convertFromEntities(repository.findAll());
    }

    /* This method check if an entity exists by id.
     *
     * @param id
     *
     * @return Boolean
     */
    public Boolean existsById(ID id) {
        return repository.findById(id).isPresent();
    }

    /* This method delete a dto.
     *
     * @param d
     */
    public void delete(DTO d) {
        repository.delete(dtoConverter.convertFromDTO(d));
    }

    /* This method delete a dto by id.
     *
     * @param id
     */
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

}
