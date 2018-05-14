import { Component, OnInit, Input } from '@angular/core';
import { BasicContent } from '../tabset/basic-content';

@Component({
  selector: 'app-tab',
  templateUrl: './tab.component.html',
  styleUrls: ['./tab.component.css']
})
export class TabComponent implements OnInit {

  @Input('tabTitle') title: string;
  @Input() active = false;
  @Input() contentRef: BasicContent;
  constructor() { }

  ngOnInit() {
  }

}
