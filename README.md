# DSL-Adapter
Compose-like RecyclerView Adapter

Make developing of your lists simpler!</br>
For now you can simply call buildAdapter(...) function and that's all, your adapter is ready to use.

The adapter is inherited from the ListAdapter, it means that you may not worry about performance issues.


**Installation:**
1. Enable viewBinding in the build.gradle of your android application or the library module
```groovy
android {
...
viewBinding {
        enable true
    }
}
```
2. Add following repositories to the settings.gradle
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
}
```
3. Add a dependency to the build.gradle of your module(you can view the last available version in the release tags section)
```groovy
dependencies {
        implementation 'com.github.Alexander1245:DSL-Adapter:1.0.0'
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
