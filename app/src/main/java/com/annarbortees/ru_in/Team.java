package com.annarbortees.ru_in;

import com.annarbortees.ru_in.com.annarbortees.ru_in.server.User;

public class Team {
    private static final User USER_STEVE = new User("spwinter@gmail.com", "Steve", "Winter", "", null);
    private static final User USER_NOT_STEVE = new User("msu@gmail.com", "Dirth", "Rug", "", null);
    public final String location;
    public final String nickname;
    public final String city;
    public final String state;
    public final String mascot;
    public final User captain;
    public final String logoAddress;

    public Team (String location, String nickname, String city, String state, String mascot,
                 User captain, String logoAddress) {
        this.location = location;
        this.nickname = nickname;
        this.city = city;
        this.state = state;
        this.mascot = mascot;
        this.captain = captain;
        this.logoAddress = logoAddress;
    }


    static Team MICHIGAN = new Team("Michigan", "Wolverines", "Ann Arbor", "MI", "", USER_STEVE,
            "http://www.google.com/imgres?imgurl=https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Michigan_Wolverines_Logo.svg/2000px-Michigan_Wolverines_Logo.svg.png&imgrefurl=https://en.wikipedia.org/wiki/1963%25E2%2580%259364_Michigan_Wolverines_men%27s_basketball_team&h=1273&w=2000&tbnid=Umyh7pngvjTVVM:&docid=OrhGzwL8ZW8a5M&ei=crfoVYC-L46xyATvsI3ADQ&tbm=isch&ved=0CDIQMygAMABqFQoTCIC_zMnj28cCFY4Ykgodb1gD2A");
    static Team MICHIGAN_STATE = new Team("Michigan State", "Spartans", "East Lansing", "MI", "Sparty", USER_NOT_STEVE,
            "https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0CAcQjRxqFQoTCOiH3fDj28cCFRYVkgodXfkDkA&url=http%3A%2F%2Fwww.americanstandard-us.com%2Fstore%2Fparts%2F&bvm=bv.101800829,d.aWw&psig=AFQjCNFd5LyB9E3BwxuMu-FGZJx1i1TEAw&ust=1441401151570732");

    public static Team[] getTeamList() {
        return new Team[]{ MICHIGAN, MICHIGAN_STATE};
    }
}

