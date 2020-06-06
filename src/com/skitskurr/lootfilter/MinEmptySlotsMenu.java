package com.skitskurr.lootfilter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.skitskurr.menumanager.ClickEvent;
import com.skitskurr.menumanager.Menu;
import com.skitskurr.menumanager.utils.ItemUtils;

public class MinEmptySlotsMenu extends Menu{

	private final Inventory inventory;
	private final Material type;
	
	public MinEmptySlotsMenu(final Material type) {
		this.inventory = Bukkit.createInventory(null, 36, "minimum free space");
		this.type = type;
		
		for(int i = 0; i < 36; i++) {
			this.inventory.setItem(i, ItemUtils.setName(new ItemStack(Material.CHEST, i + 1), "minimum " + (i + 1) + " empty " + (i == 0 ? "slot" : "slots")));
		}
	}
	
	@Override
	protected Inventory getInventory(final Player player) {
		return this.inventory;
	}
	
	@Override
	public void onClick(final ClickEvent event) {
		final Player player = event.getPlayer();
		LootFilterSection.getSection(player, this.type).ifPresent(section -> section.setMinEmptySlots(event.getSlot() + 1));
		super.back(player);
	}

}
