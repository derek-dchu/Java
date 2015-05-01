package multithreading;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class ImmutablePerson {
    private final String name;
    private final int age;
    private final Date birthday;
    private final List<ImmutablePerson> friends;

    public ImmutablePerson(String name, int age, Date birthday, List<ImmutablePerson> friends) {
        this.name = name;
        this.age = age;
        this.birthday = new Date(birthday.getTime());
        this.friends = Collections.unmodifiableList(friends);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthday() {
        return new Date(birthday.getTime());
    }

    public List<ImmutablePerson> getFriends() {
        return friends;
    }
}
