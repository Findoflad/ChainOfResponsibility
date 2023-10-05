import java.util.concurrent.ThreadLocalRandom;

/*Паттерн проектирования "Chain of Responsibility" (Цепочка обязанностей) является поведенческим паттерном,
  который позволяет передавать запросы последовательно по цепочке обработчиков.
  Каждый потенциальный обработчик содержит ссылку на следующий обработчик в цепи.
  Если один из обработчиков не может обработать запрос, он передает его следующему обработчику в цепи.

  Структура паттерна:
    Handler (Обработчик): Определяет интерфейс для обработки запросов и содержит ссылку на следующий обработчик в цепи.

    ConcreteHandler (Конкретный обработчик): Реализует интерфейс Handler и обрабатывает запросы, для которых он отвечает.
        Если не способен обработать запрос, он передает запрос следующему обработчику в цепи.

    Client (Клиент): Создает цепочку обработчиков и передает запросы одному из обработчиков в этой цепи.*/

public class Main {
    public static void main(String[] args) {

        Programmer intern = new Intern("Stepa");
        Programmer junior = new Junior("Petya");
        Programmer senior = new Senior("Vika");
        Programmer middle = new Middle("Arkasha");

        senior.SetSuccessor(middle);
        //middle.SetSuccessor(junior);
        //.SetSuccessor(intern);

        senior.Handle("New startUp");
    }
}

interface TaskHandler {
    public void SetSuccessor(Object object);

    public void Handle(String task);
}

abstract class Programmer implements TaskHandler {
    public String name;
    protected Programmer successor = null;

    public void SetSuccessor(Object object) {
        try {
            successor = (Programmer) object;
        } catch (ClassCastException exception) {
            System.out.println("Ошибка преобразования типов");
        }
    }

    public Programmer(String name) {
        this.name = name;
    }

    public void DrinkCoffee() {
        for (int i = 0; i < 1000; ++i) {
            continue;
        }
    }
}

class Intern extends Programmer {
    public Intern(String name) {
        super(name);
    }

    @Override
    public void Handle(String task) {
        System.out.println("Intern " + name + " accepted " + task);
    }
}

class Junior extends Programmer {
    public Junior(String name) {
        super(name);
    }

    @Override
    public void Handle(String task) {
        if (ThreadLocalRandom.current().nextInt(0, 2) == 1) {
            System.out.println("Junior " + name + " accepted " + task);
        } else {
            try {
                successor.Handle(task);
            }catch(NullPointerException e){
                System.out.println("Преемник был null");
            }
        }
    }
}

class Middle extends Programmer {
    public Middle(String name) {
        super(name);
    }

    @Override
    public void Handle(String task) {
        if (ThreadLocalRandom.current().nextInt(0, 4) == 1) {
            System.out.println("Middle " + name + " accepted " + task);
        } else {
            try {
                successor.Handle(task);
            }catch(NullPointerException e){
                System.out.println("Преемник был null");
            }
        }
    }
}

class Senior extends Programmer {
    public Senior(String name) {
        super(name);
    }

    @Override
    public void Handle(String task) {
        if (ThreadLocalRandom.current().nextInt(0, 8) == 1) {
            System.out.println("Senior " + name + " accepted " + task);
        } else {
            try {
                successor.Handle(task);
            }catch(NullPointerException e){
                System.out.println("Преемник был null");
            }
        }
    }
}