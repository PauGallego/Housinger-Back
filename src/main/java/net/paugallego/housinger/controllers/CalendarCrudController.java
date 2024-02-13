package net.paugallego.housinger.controllers;

import net.paugallego.housinger.model.database.entities.CalendarEntity;
import net.paugallego.housinger.model.database.entities.ChatEntity;
import net.paugallego.housinger.model.database.repositories.CalendarRepository;
import net.paugallego.housinger.model.database.repositories.ChatRepository;
import net.paugallego.housinger.model.dto.CalendarDTO;
import net.paugallego.housinger.model.dto.ChatDTO;
import net.paugallego.housinger.services.crud.dto.CalendarDTOConverter;
import net.paugallego.housinger.services.crud.dto.ChatDTOConverter;
import net.paugallego.housinger.services.crud.entity.CalendarCRUDService;
import net.paugallego.housinger.services.crud.entity.ChatCRUDService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/calendar")
public class CalendarCrudController extends AbstractController<CalendarEntity, CalendarDTO, CalendarCRUDService, CalendarDTOConverter, CalendarRepository, Long> {
}
