import { Injectable } from '@angular/core';
import { Food } from '../shared/models/Food';
import { sample_foods,sample_tags } from 'src/data';
import { Tag } from '../shared/models/Tags';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  constructor() { }

  getAll():Food[]{
    return sample_foods;
  }

  getAllFoodsBySearchTerm(searchTerm: string): Food[]{
    return this.getAll().filter(food => food.name.toLocaleLowerCase().includes(searchTerm.toLocaleLowerCase()))
  }

  getAllTags():Tag[]{
    return sample_tags
  }

  getAllFoodsByTag(tag: string): Food[]{
    return tag == "All" ? this.getAll() : this.getAll().filter(food => food.tags?.includes(tag))
  }

  getFoodById(foodId: string): Food{
    // si il y'a pas d'aliment définie alors on retourne un aliment vide
    return this.getAll().find(food => food.id == foodId) ?? new Food()
  }
}
