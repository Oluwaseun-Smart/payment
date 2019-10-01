import {Injectable} from '@angular/core';
import {School} from "./School";
import {Department} from "./Department";

@Injectable()
export class DataService {

  getSchool() {
    return [
      new School(1, 'School of Science and Computer studies'),
      new School(2, 'School of Business Studies'),
      new School(3, 'School of Engineering'),
      new School(4, 'School of Environmental')
    ];
  }

  getDepartment() {
    return [
      new Department(1, 1, 'Computer Science'),
      new Department(2, 1, 'Food Technology'),
      new Department(3, 1, 'Glass & Ceramic Technology'),
      new Department(4, 1, 'Science Technology'),
      new Department(5, 1, 'Statistics'),
      new Department(6, 2, 'Accountancy'),
      new Department(7, 2, 'Business Administration and Management'),
      new Department(8, 2, 'Banking and Finance'),
      new Department(9, 2, 'Marketing'),
      new Department(10, 2, 'Office Technology and Management'),
      new Department(11, 2, 'Purchasing and Supply'),
      new Department(12, 3, 'Agricultural Technology'),
      new Department(13, 3, 'Fishery'),
      new Department(14, 3, 'Civil Engineering'),
      new Department(15, 3, 'Mechanical Engineering'),
      new Department(16, 3, 'Electrical and Electronics'),
      new Department(17, 3, 'Mineral and Petroleum Resource'),
      new Department(18, 3, 'Agric and Bio-Environmental'),
      new Department(19, 4, 'Architectural Technology'),
      new Department(20, 4, 'Building Technology'),
      new Department(21, 4, 'Estate Management'),
      new Department(22, 4, 'Quantity Surveying'),
      new Department(23, 4, 'Surveying & Geo-informatics'),
      new Department(24, 4, 'Urban and Regional Planning')
    ];
  }
}
