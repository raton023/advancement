private void logro(Player p) {
		NamespacedKey id= new NamespacedKey(m, "story/" + UUID.randomUUID().toString());
		final String json= "{\"criteria\":{\"elytra\":{\"trigger\":\"minecraft:impossible\",\"conditions\":{\"items\":[]}}},\"display\":{\"show_toast\":true,\"background\":\"minecraft:textures/blocks/bedrock.png\",\"icon\":{\"item\":\"minecraft:nether_star\"},\"description\":\"§3Pide un Deseo a Shenlong\",\"title\":\"§2Wish Upon a Shenlong\",\"announce_to_chat\":true,\"frame\":\"goal\"}}";
		if(Bukkit.getAdvancement(id) == null)Bukkit.getUnsafe().loadAdvancement(id, json);
		if(!p.getAdvancementProgress(Bukkit.getAdvancement(id)).isDone())p.getAdvancementProgress(Bukkit.getAdvancement(id)).awardCriteria("elytra");
		Bukkit.getScheduler().runTaskLater(m, new Runnable() {
			@Override
			public void run() {
				if(p.getAdvancementProgress(Bukkit.getAdvancement(id)).isDone())p.getAdvancementProgress(Bukkit.getAdvancement(id)).revokeCriteria("elytra");
				Bukkit.getScheduler().runTaskLater(m, new Runnable() {
					@Override
					public void run() {
						CraftMagicNumbers.INSTANCE.removeAdvancement(id);
					}
				}, 5);
				
				
			}
		}, 10);
	
	
	}
