import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { MusicComponent } from './music/music.component';
import { SearchComponent } from './search/search.component';
import { MusicListComponent } from './music-list/music-list.component';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
    declarations: [MusicComponent, SearchComponent, MusicListComponent],
    imports: [
        HttpClientModule,
        CommonModule,
        ReactiveFormsModule]
})
export class MusicsModule { }
