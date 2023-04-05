# DSL-Adapter
Compose-like RecyclerView Adapter

Make developing of your lists simpler!</br>
For now you can simply call buildAdapter(...) function and that's all, your adapter is ready to use.


1. Enable viewBinding in build.gradle of your android application or library module
```groovy
android {
...
viewBinding {
        enable true
    }
}
```

Single ViewHolder example:
```kotlin
val adapter = buildAdapter<Pet, ItemPetBinding> { 
        diffCallback { old, new -> old.id == new.id }
        items {
            viewHolder<Pet, ItemPetBinding> { pet, binding ->
                binding.textViewPetName.text = pet.name
                binding.root.setOnClickListener { viewModel.onPetClick(pet) }
            }
        }
    }
```
Multiple ViewHolders example:
```kotlin
val adapter = buildAdapter<Any, ViewBinding> {
        diffCallback { old, new -> old === new }
        items {
            viewHolder<Pet, ItemPetBinding> { pet, binding ->
                binding.textViewPetName.text = pet.name
                binding.root.setOnClickListener { viewModel.onPetClick(pet) }
            }
            viewHolder<Person, ItemPersonBinding> { person, binding ->
                binding.textViewPersonName.text = person.fullName
                binding.root.setOnClickListener { viewModel.onPersonClick(person) }
            }
        }
    }
```
