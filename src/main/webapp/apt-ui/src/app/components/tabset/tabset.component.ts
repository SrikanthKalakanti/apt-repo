import { Component, OnInit, AfterContentInit, ContentChildren, QueryList } from '@angular/core';
import { TabComponent } from '../tab/tab.component';
// import { BulkUploadHistoryComponent } from '../contract-upload-view/bulk-upload-history/bulk-upload-history.component';
// import { UploadService, ErrorService } from '../../shared';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { BasicContent } from './basic-content';

@Component({
  selector: 'app-tabset',
  templateUrl: './tabset.component.html',
  styleUrls: ['./tabset.component.css']
})
export class TabsetComponent implements AfterContentInit, OnInit {

  private contentSbj = new BehaviorSubject<BasicContent>(null);
  content = this.contentSbj.asObservable();

  constructor(
    // private uploadService: UploadService,
    // private errorService: ErrorService
  ) { }

  ngOnInit() {
  }

  @ContentChildren(TabComponent) tabs: QueryList<TabComponent>;

  // contentChildren are set
  ngAfterContentInit() {
    setTimeout(() => {
      // get all active tabs
      const activeTabs = this.tabs.filter((tab) => tab.active);

      // if there is no active tab set, activate the first
      if (activeTabs.length === 0) {
        this.selectTab(this.tabs.first);
      }
    });
  }

  selectTab(tab: TabComponent) {
    const tabs = this.tabs.toArray();
    tabs.forEach(tab => tab.active = false);
    setTimeout(() => tab.active = true);
    setTimeout(() => this.contentSbj.next(tab.contentRef));
  }
}
