package me.mrgeneralq.sleepmost.commands.subcommands;

import me.mrgeneralq.sleepmost.commands.ErrorCommand;
import me.mrgeneralq.sleepmost.commands.dream.*;
import me.mrgeneralq.sleepmost.enums.MessageKey;
import me.mrgeneralq.sleepmost.interfaces.IDreamService;
import me.mrgeneralq.sleepmost.interfaces.IMessageService;
import me.mrgeneralq.sleepmost.interfaces.ISubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

import static me.mrgeneralq.sleepmost.statics.ChatColorUtils.colorize;

public class DreamSubCommand implements ISubCommand {

    private final Map<String, ISubCommand> subCommands = new HashMap<>();

    private final IDreamService dreamService;
    private final IMessageService messageService;

    public DreamSubCommand(IDreamService dreamService, IMessageService messageService) {
        this.dreamService = dreamService;
        this.messageService = messageService;
        this.registerSubCommands();
    }


    private void registerSubCommands(){
        this.subCommands.put("create", new DreamCreateCommand(this.dreamService, this.messageService));
        this.subCommands.put("remove", new DreamRemoveCommand(this.dreamService, this.messageService));
        this.subCommands.put("setchance", new DreamSetChanceCommand(this.dreamService, this.messageService));
        this.subCommands.put("setspawn", new DreamSetSpawnCommand(this.dreamService, this.messageService));
        this.subCommands.put("removespawn", new DreamRemoveSpawnCommand(this.dreamService, this.messageService));
        this.subCommands.put("setduration", new DreamSetDurationCommand(this.dreamService, this.messageService));
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String commandLabel, String[] args) {


        //todo check
        if(args.length == 1){

            if(!sender.hasPermission("sleepmost.dream")){
                this.messageService.sendMessage(sender, messageService.getMessagePrefixed(MessageKey.NO_PERMISSION_COMMAND).build());
                return true;
            }
            sender.sendMessage(colorize("&b*********************************************"));
            sender.sendMessage(colorize("&b*&e  SLEEPMOST &o&7author: MrGeneralQ  &b*"));
            sender.sendMessage(colorize("&b*********************************************"));
            sender.sendMessage(colorize("&e/sm dream create <name> [type] &f create a new dream"));
            sender.sendMessage(colorize("&e/sm dream remove <name> &fremove an existing dream"));
            sender.sendMessage(colorize("&e/sm dream setspawn [name] &fset spawn-location for dream"));
            sender.sendMessage(colorize("&e/sm dream removespawn <name> &fremove spawn-location by name"));
            sender.sendMessage(colorize("&e/sm dream setchance <name> <chance> &fset chance for having the dream"));
            sender.sendMessage(colorize("&e/sm dream setduration <name> <duration> &fset the duration of the dream"));
            return true;
        }

        String subCommandStr = args[1].toLowerCase();

        // check if player has permission of command
        if(!sender.hasPermission("sleepmost." + subCommandStr))
        {
            this.messageService.sendMessage(sender, messageService.getMessagePrefixed(MessageKey.NO_PERMISSION_COMMAND).build());
            return true;
        }

        ISubCommand subCommand = subCommands.getOrDefault(subCommandStr, new ErrorCommand(messageService));
        return subCommand.executeCommand(sender, command, commandLabel, Arrays.copyOfRange(args, 2, args.length));

    }

}
