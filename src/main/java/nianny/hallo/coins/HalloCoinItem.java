package nianny.hallo.coins;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Pair;

import java.util.*;

public class HalloCoinItem extends Item {
    private static final Comparator<Pair<String, Integer>> byValue = Comparator.comparingInt(Pair::getRight);
    public static Map<String, Integer> val = new HashMap<>();
    public HalloCoinItem(Settings settings,String name, int value) {
        super(settings);
        val.put(name, value); //store type of coin to value
        HalloCoins.convert.put(name,this);
    }
    public static int GetBalance(PlayerEntity player){
        UUID playeruuid = player.getUuid(); //get UUID cuz usernames might be the same with cracked launchers
        return HalloCoins.mp.get(playeruuid);
    }
    public static Map<String, Integer> Withdraw(PlayerEntity player, int withAmount){
         Map<String,Integer> welp = new HashMap<>();
        int bal = GetBalance(player);
        try {
            HalloCoins.mp.replace(player.getUuid(), bal, bal - withAmount); //update balance
        }catch(Exception e){
            throw new AssertionError("That was NOT supposed to happen...");
        }
        List<Pair<String,Integer>> st = new ArrayList<>();

        for(var entry: val.entrySet()){
            st.add(new Pair<>(entry.getKey(),entry.getValue()));
        }
        st.sort(byValue);
        Collections.reverse(st);
        for(var entry:st){
            int t = withAmount / entry.getRight();
            welp.put(entry.getLeft(), t);
            //HalloCoins.LOGGER.info("t = " + t + ", \nentry.getLeft() = " + entry.getLeft() + ",\n entry.getRight() = "+ entry.getRight() + ", \n withdrawalamt = " + withAmount);
            withAmount -= t*entry.getRight();
        }
        return welp;
    }
    public static int GetBalanceFromCoin(String coin, int count){ // can only dep one type of coin at a time cuz mainhand/offhand can only hold 1 type of item
        try{
            return val.get(coin) * count;
        }catch(Exception e){
            throw new AssertionError("Coin not defined!" ); //should not run by right
        }
    }

    public static void Deposit(String coin, int count, PlayerEntity player){
        int bal = GetBalance(player);
        try {
            HalloCoins.mp.replace(player.getUuid(), bal, bal + GetBalanceFromCoin(coin, count)); //update balance
        }catch(Exception e){
            throw new AssertionError("That was NOT supposed to happen...");
        }
    }
}
