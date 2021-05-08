package utils;


import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import startClasses.*;
import utils.ZonedDateTimeAdapter;

/**
 * Класс для коллеции и операций над ней
 */
@XmlRootElement(name = "Flats")
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectionManager {
    @XmlTransient
    private java.time.ZonedDateTime creationDate;

    @XmlElement(name = "flat")
    private LinkedList<Flat> flats = new LinkedList<Flat>();
    @XmlTransient
    private CommandManager commandManager;

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public CollectionManager() {
        creationDate = ZonedDateTime.now();
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    /**
     * Метод, выводящий информацию о коллекции
     */
    public String getInfo() {
        return "Размер коллекции: " + flats.size()+
        "Дата создания коллекции: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(creationDate);
    }

    /**
     * Метод, удаляющий элемент по id
     * @param id id элемента, который надо удалить
     * @return Возвращает true, если элемент с таким id существует
     */
    public boolean removeId(Long id) {
        for (Flat flat : flats) {
            if (flat.getId().equals(id)) {
                flats.remove(flat);
                return true;
            }
        }
        return false;
    }

    public LinkedList<Flat> getFlats() {
        return flats;
    }

    /**
     * Выводит первый элемент массива
     */
    public String head() throws IndexOutOfBoundsException {
        try {
            return flats.get(0).niceToString();
        } catch (IndexOutOfBoundsException e) {
            return "Коллекция пуста";
        }
    }
    public String removeHead() throws IndexOutOfBoundsException {
        try {
            String firstFlat = flats.get(0).niceToString();
            flats.removeFirst();
            return firstFlat;
        } catch (IndexOutOfBoundsException e) {
            return "Коллекция пуста";
        }
    }
    public void add(Flat a){
        flats.add(a);
    }

    public String getUniquePrice(){
        return flats.stream()
                .map(flat -> flat.getPrice().toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Очищает коллекцию
     * @return Возвращает false, если коллекция - пуста
     */
    public boolean clear() {
        if(flats.size()==0)return false;
        flats.clear();
        return true;
    }

    public String getFieldDescendingHouse(){
        return flats.stream()
                .map(Flat::getHouse)
                .map(House::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Выводит все элемент коллекции
     */
    public String show() {
        if(flats.size()==0)return "Коллекция пуста";
        return flats.stream()
                .map(Flat::niceToString)
                .collect(Collectors.joining("\n"));
    }

    public String updateId(Long id, Flat flat){
        Optional<Flat> flatById= flats.stream()
                .filter(x-> x.getId().equals(id))
                .findFirst();
        if(flatById.isPresent()){
            flats.stream()
                    .filter(x -> x.getId().equals(id))
                    .forEach(x -> x=flat);
            return "Квартира с данным id успешно удалена.";
        }
        return "Квартиры с данным id не существует";
    }
    /**
     * Добавляет элемент в коллекцию
     */
    public void addElement(Flat a) {
        flats.add(a);
    }

    /**
     * Сортирует коллекцию
     */
    public void sort(){
        Collections.sort(flats);
    }


}
