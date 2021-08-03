package flipkart.conferenceroom.services;

import flipkart.conferenceroom.entities.BaseEntity;
import flipkart.conferenceroom.entities.Floor;

import java.util.Optional;

public interface IService {

    BaseEntity add(String serviceObject);

   BaseEntity get(String serviceObjectName);
}
