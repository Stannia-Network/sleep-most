package me.mrgeneralq.sleepmost.commands.dream;

import me.mrgeneralq.sleepmost.enums.DreamType;
import me.mrgeneralq.sleepmost.interfaces.IDreamService;
import me.mrgeneralq.sleepmost.interfaces.IMessageService;
import me.mrgeneralq.sleepmost.interfaces.ISubCommand;
import me.mrgeneralq.sleepmost.models.Dream;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DreamSetChanceCommand implements ISubCommand {
    private final IDreamService dreamService;
    private final IMessageService messageService;

    public DreamSetChanceCommand(IDreamService dreamService, IMessageService messageService) {
        this.dreamService = dreamService;
        this.messageService = messageService;
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        //ARS <dreamName> [type]

        if(args.length == 0){
            //MSG specify a dream
            return true;
        }

        String dreamName = args[0];

        if(!this.dreamService.dreamExists(dreamName)){
            this.messageService.sendMessage(sender,
                    this.messageService.getMessagePrefixed("&cThis dream does not exist!").build());
            return true;
        }

        Dream dream = dreamService.getDream(dreamName);

        if(args.length < 2){
           this.messageService.sendMessage(sender,
                   this.messageService.getMessagePrefixed("&cMissing chance value. Please provide a value between <0.0 -100.0>").build());
           return true;
        }

        String chanceString = args[2];
        double chance = 0.0;

        try{

            chance = Double.parseDouble(chanceString);
        }catch (Exception e){
            this.messageService.sendMessage(sender,
                    this.messageService.getMessagePrefixed("&cInvalid format! Please provide a value between <0.0 -100.0>").build());
            return true;
        }
        //todo check if chance should be changed to minimum 0 and max 100
        dream.setChance(chance);
        dreamService.updateDream(dream);

        //todo message
        this.messageService.sendMessage(sender, messageService.getMessagePrefixed("&eThe dream has been updated!")
                .setDream(dream)
                .build());
        return true;
    }
}
