package model;

public class QuestModel<T> extends AbstractTask<T> {

    public QuestModel(String name, Integer price, T questCategory ) {

        this.name = name;
        this.price = price;
        this.questCategory = questCategory;

    }

}
